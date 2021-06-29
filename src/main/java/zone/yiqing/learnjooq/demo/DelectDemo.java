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
public class DelectDemo {

  public static void main(String[] args) {
    delete();
  }

  public static void delete() {
    DSLContext dslContext = DslContextConfig.getDslContext();
    dslContext.delete(AUTHOR)
        .where(AUTHOR.LAST_NAME.eq("li"))
        .execute();
  }
}
