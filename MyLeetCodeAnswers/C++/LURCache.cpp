//UnDone!
#include <iostream>
#include <deque>
using namespace std;

class LRUCache
{
private:
    class Item
    {
    public:
        int key;
        int value;
        Item(int k, int v): key(k), value(v) {}
    };

public:
    int cap;
    deque<Item> pool;
    LRUCache(int capacity)
    {
        this->cap = capacity;
    }

    int get(int key)
    {
        int result = -1;
        for (int i = 0; i < pool.size(); ++i)
        {
            if (pool[i].key == key)
            {
                return pool[i].value;
            }
        }
        return -1;
    }

    void set(int key, int value) {
	int pos = findItem(key);
        if (pool.size() == cap) {
            removeLRUItem();
        }
        pool.push_front(Item(key,value));
    }

    int findItem(int key) {
	int pos = -1;
	for (int i=0; i<pool.size(); ++i) {
	   if (pool[i].key == key) {
		return i;
	   }
	}
	return -1;
    } 

    void removeLRUItem()
    {
    }

};
int main() {
    LRUCache lruCache(6);
    lruCache.set(1, 1);
    lruCache.set(2, 2);
    lruCache.set(3, 3);
    lruCache.set(4, 4);
    std::cout << lruCache.get(2) << std::endl;
    return 0;
}
