package zone.yiqing.learnjooq.demo;

import org.jooq.DSLContext;
import org.jooq.Result;
import zone.yiqing.learnjooq.config.DslContextConfig;
import zone.yiqing.learnjooq.generated.tables.records.AuthorRecord;

import static zone.yiqing.learnjooq.generated.Tables.*;

/**
 * @author yiqing.zhang, {@literal <yiqing.zhang@leyantech.com>}
 * @date 2021-06-29.
 */
public class InsertDemo {

  public static void main(String[] args) {
    insert();
  }

  public static void insert(){
    DSLContext dslContext = DslContextConfig.getDslContext();

    // 方法 1
    try{
      dslContext.insertInto(AUTHOR,AUTHOR.ID,AUTHOR.FIRST_NAME,AUTHOR.LAST_NAME)
          .values(4,"mazi","wang")
          .execute();
    }catch (Exception e){}


    // 方法 2
    dslContext.insertInto(AUTHOR)
        .set(AUTHOR.ID, 5)
        .set(AUTHOR.FIRST_NAME, "si")
        .set(AUTHOR.LAST_NAME, "wang")
        .execute();
  }
}
