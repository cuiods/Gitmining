package edu.nju.git.datavisitors.repovisitors;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import edu.nju.git.PO.RepoBriefPO;
import edu.nju.git.PO.RepoPO;
import edu.nju.git.VO.RepoBriefVO;
import edu.nju.git.constant.Consts;
import edu.nju.git.data.service.RepoDataService;
import edu.nju.git.tools.POVOConverter;
import edu.nju.git.type.SortType;

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

    public int getPage(){
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public boolean getReverse(){
        return reverse;
    }

    public void setReverse(boolean reverse) {
        this.reverse = reverse;
    }

    public abstract List<RepoBriefVO> visit(final RepoDataService repoDataService);

    protected List<RepoBriefVO> visit(RepoDataService userDataService, SortType sortType) {
        List<RepoPO> POList = null;
		try {
			POList = userDataService.getRepoPOs(sortType);
		} catch (Exception e) {
			e.printStackTrace();
		}
        List<RepoBriefVO> resultList = new ArrayList<>();
        int totalItem = 0;
		try {
			totalItem = userDataService.getTotalCount();
		} catch (Exception e) {
			e.printStackTrace();
		}
        if ((page-1)* Consts.PAGE_CAPACITY < totalItem) {
            if (reverse) {
                ListIterator<RepoPO> itr = POList.listIterator(totalItem - (page-1)*Consts.PAGE_CAPACITY);
                for (int i=0; i<Consts.PAGE_CAPACITY; i++) {
                    if (itr.hasPrevious()){
                        resultList.add(POVOConverter.convertToBriefVO(itr.previous()));
                    }
                    else {
                        break;
                    }
                }
            }
            else {
                ListIterator<RepoPO> itr = POList.listIterator((page-1)*Consts.PAGE_CAPACITY);
                for (int i=0;i<Consts.PAGE_CAPACITY; i++) {
                    if (itr.hasNext()) {
                        resultList.add(POVOConverter.convertToBriefVO(itr.next()));
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
