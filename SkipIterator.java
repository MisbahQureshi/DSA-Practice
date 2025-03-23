// https://leetcode.com/discuss/interview-question/341818

class SkipIterator implements Iterator<Integer> {
    Iterator<Integer> it;
    HashMap<Integer, Integer> map;
    Integer nextEl;
    public SkipIterator(Iterator<Integer> it) {
        this.it = it;
        map = new HashMap<>();
        advance();
    }

    @Override
    public boolean hasNext() {
        return nextEl!=null;
    }

    @Override
    public Integer next() {
       Integer temp = nextEl;
        advance();
        return temp;
    }

    public void skip(int num) {
        if(num==nextEl) advance();
        else{
            if (map.containsKey(num)) map.put(num, map.get(num) + 1);
            else map.put(num, 1);
        }
    }

    private void advance() {
        nextEl = null;
        while(nextEl == null && it.hasNext()){
            Integer el = it.next();
            if(map.containsKey(el)){
                map.put(el, map.get(el)-1);
                map.remove(el,0);
            } else{
                nextEl = el;
            }
        }
    }
}

public class Main {
        public static void main(String[] args) {
        SkipIterator it = new SkipIterator(Arrays.asList(1, 2, 3).iterator());
        System.out.println(it.hasNext());
        it.skip(2);
        it.skip(1);
        it.skip(3);
        System.out.println(it.hasNext());
    }
}
