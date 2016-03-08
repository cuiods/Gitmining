package edu.nju.git.datavisitors.repovisitors;

import edu.nju.git.PO.RepoBriefPO;
import edu.nju.git.VO.RepoBriefVO;
import edu.nju.git.constant.Consts;
import edu.nju.git.data.service.RepoDataService;
import edu.nju.git.tools.POVOConverter;
import edu.nju.git.type.SortType;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by harry on 16-3-8.
 */
public abstract class SimpleRepoVisitor implements RepoVisitor{
    /**
     * the page number you want to get
     */
    protected int page;
    /**
     * true if the name order is form high to low
     */
    protected boolean reverse;

    protected SimpleRepoVisitor(int page, boolean reverse) {
        this.page = page;
        this.reverse = reverse;
    }

    protected List<RepoBriefVO> visit(RepoDataService userDataService, SortType sortType) {
        List<RepoBriefPO> POList = userDataService.getRepoBriefPOs(sortType);
        List<RepoBriefVO> resultList = new ArrayList<>();
        int totalItem = userDataService.getTotalCount();
        if ((page-1)* Consts.PAGE_CAPACITY < totalItem) {
            if (reverse) {
                ListIterator<RepoBriefPO> itr = POList.listIterator(totalItem - (page-1)*Consts.PAGE_CAPACITY);
                for (int i=0; i<Consts.PAGE_CAPACITY; i++) {
                    if (itr.hasPrevious()){
                        resultList.add(POVOConverter.convert(itr.previous()));
                    }
                    else {
                        break;
                    }
                }
            }
            else {
                ListIterator<RepoBriefPO> itr = POList.listIterator((page-1)*Consts.PAGE_CAPACITY);
                for (int i=0;i<Consts.PAGE_CAPACITY; i++) {
                    if (itr.hasNext()) {
                        resultList.add(POVOConverter.convert(itr.next()));
                    }
                    else {
                        break;
                    }
                }
            }
        }
        return resultList;
    }
}
