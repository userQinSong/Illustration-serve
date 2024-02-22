package alibaba.illustration.common.pageHelper;

import alibaba.illustration.entity.ill.pageResult.CommentPageResult;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;

import java.util.List;

public class MyPageHelper<T> {
    public PageInfo<T> toListPageInfo(List<T> list, int pageNum, int pageSize){
//创建Page类
        Page<T> page = new Page<T>(pageNum, pageSize);
//为Page类中的total属性赋值
        int total = list.size();
        page.setTotal(total);
//计算当前需要显示的数据下标起始值
        int startIndex = (pageNum - 1) * pageSize;
        int endIndex = Math.min(startIndex + pageSize,total);
//从链表中截取需要显示的子链表，并加入到Page
        if(startIndex < endIndex) {
            page.addAll(list.subList(startIndex, endIndex));
//以Page创建PageInfo
            return new PageInfo<T>(page);
        }else
            return null;
    }
}
