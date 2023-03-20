package hello.itemservice.domain.item;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ItemRepository {
    private static final Map<Long, Item> store = new HashMap<>(); // 실무 (멀티 쓰레드 환경) 에서는 HashMap 쓰면 안됨 -> ConcurrentHashMap
    private static long sequence = 0L; // 마찬가지 -> Atomic

    public Item save(Item item) {
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }

    public Item findById(Long id) {
        return store.get(id);
    }

    public List<Item> findAll() {
        return new ArrayList<>(store.values()); // 방어적 복사. 깊은 복사
    }

    public void updateItem(Long itemId, Item updateParam) {
        Item item = findById((itemId));

        item.setItemName(updateParam.getItemName());
        item.setPrice(updateParam.getPrice());
        item.setQuantity(updateParam.getQuantity());
        // ItemParamDto 클래스 따로 정의하는 게 더 좋음 ( id를 사용하지 않으므로 )
    }

    public void clearStore() {
        store.clear();
    }
}
