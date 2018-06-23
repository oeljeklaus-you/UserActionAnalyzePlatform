package cn.edu.hust.session;


import scala.math.Ordered;

public class CategorySortKey implements Ordered<CategorySortKey>, java.io.Serializable {

    private Long clickCount;
    private Long orderCount;
    private Long payCount;

    @Override
    public int compare(CategorySortKey categorySortKey) {
        if(clickCount-categorySortKey.getClickCount()!=0)
        {
            return (int) (clickCount-categorySortKey.getClickCount());
        }else if(clickCount-categorySortKey.getClickCount()==0&&orderCount-categorySortKey.getOrderCount()!=0)
        {
            return (int) (orderCount-categorySortKey.getOrderCount());
        }
        else if(clickCount-categorySortKey.getClickCount()==0&&orderCount-categorySortKey.getOrderCount()==0&&payCount-categorySortKey.getPayCount()!=0)
            return (int) (payCount-categorySortKey.getPayCount());
        return 0;
    }

    @Override
    public boolean $less(CategorySortKey categorySortKey) {
        if(clickCount<categorySortKey.getClickCount())
        {
            return true;
        }else if(clickCount==categorySortKey.getClickCount()&&orderCount<categorySortKey.getOrderCount())
        {
            return true;
        }
        else if(clickCount==categorySortKey.getClickCount()&&orderCount==categorySortKey.getOrderCount()&&payCount<categorySortKey.getPayCount())
            return true;
        return false;
    }

    @Override
    public boolean $greater(CategorySortKey categorySortKey) {
        if(clickCount>categorySortKey.getClickCount())
        {
            return true;
        }else if(clickCount==categorySortKey.getClickCount()&&orderCount>categorySortKey.getOrderCount())
        {
            return true;
        }
        else if(clickCount==categorySortKey.getClickCount()&&orderCount==categorySortKey.getOrderCount()&&payCount>categorySortKey.getPayCount())
            return true;

        return false;
    }

    @Override
    public boolean $less$eq(CategorySortKey categorySortKey) {
        if($less(categorySortKey))
        {
            return true;
        }
        else if(clickCount==categorySortKey.getClickCount()&&orderCount==categorySortKey.getOrderCount()&&payCount==categorySortKey.getPayCount())
            return true;
        return false;
    }

    @Override
    public boolean $greater$eq(CategorySortKey categorySortKey) {
        if($greater(categorySortKey))
        {
            return true;
        }else if(clickCount==categorySortKey.getClickCount()&&orderCount==categorySortKey.getOrderCount()&&payCount==categorySortKey.getPayCount())
            return true;
        return false;
    }

    @Override
    public int compareTo(CategorySortKey categorySortKey) {
        if(clickCount-categorySortKey.getClickCount()!=0)
        {
            return (int) (clickCount-categorySortKey.getClickCount());
        }else if(clickCount-categorySortKey.getClickCount()==0&&orderCount-categorySortKey.getOrderCount()!=0)
        {
            return (int) (orderCount-categorySortKey.getOrderCount());
        }
        else if(clickCount-categorySortKey.getClickCount()==0&&orderCount-categorySortKey.getOrderCount()==0&&payCount-categorySortKey.getPayCount()!=0)
            return (int) (payCount-categorySortKey.getPayCount());
        return 0;
    }

    public Long getClickCount() {
        return clickCount;
    }

    public void setClickCount(Long clickCount) {
        this.clickCount = clickCount;
    }

    public Long getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(Long orderCount) {
        this.orderCount = orderCount;
    }

    public Long getPayCount() {
        return payCount;
    }

    public void setPayCount(Long payCount) {
        this.payCount = payCount;
    }

    public void set(Long clickCount, Long orderCount, Long payCount) {
        this.clickCount = clickCount;
        this.orderCount = orderCount;
        this.payCount = payCount;
    }
}
