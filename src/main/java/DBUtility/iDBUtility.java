package DBUtility;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: snowhyzhang
 * Date: 12-11-9
 * Time: 上午3:02
 * To change this template use File | Settings | File Templates.
 */
public interface iDBUtility {
    public void getConnection();

    public List getData(String SQL);
    public List getAllData();

    public void disConnection();
}
