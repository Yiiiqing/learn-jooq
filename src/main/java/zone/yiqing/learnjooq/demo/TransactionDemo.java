package zone.yiqing.learnjooq.demo;

import org.jooq.DSLContext;
import zone.yiqing.learnjooq.config.DslContextConfig;
import zone.yiqing.learnjooq.generated.tables.records.AuthorRecord;

import static zone.yiqing.learnjooq.generated.Tables.*;

/**
 * @author yiqing.zhang, {@literal <yiqing.zhang@leyantech.com>}
 * @date 2021-06-29.
 */
public class TransactionDemo {

  public static void main(String[] args) {
    transaction();
  }

  private static void transaction() {
    DSLContext dslContext = DslContextConfig.getDslContext();
    dslContext.transaction(configuration -> {
      AuthorRecord peng = dslContext.insertInto(AUTHOR)
          .set(AUTHOR.ID, 6)
          .set(AUTHOR.FIRST_NAME, "6")
          .set(AUTHOR.LAST_NAME, "peng")
          .returning()
          .fetchOne();
      int i=1/0;
    });
  }
}
