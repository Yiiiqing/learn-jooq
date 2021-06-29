package zone.yiqing.learnjooq.demo;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.Result;
import zone.yiqing.learnjooq.config.DslContextConfig;

import static zone.yiqing.learnjooq.generated.Tables.*;

/**
 * @author yiqing.zhang, {@literal <yiqing.zhang@leyantech.com>}
 * @date 2021-06-29.
 */
public class SelectDemo {

  public static void main(String[] args) {
    selectDemo();
  }

  public static void selectDemo() {
    DSLContext dslContext = DslContextConfig.getDslContext();
    Result<Record> result = dslContext.select().from(AUTHOR)
        .where(AUTHOR.FIRST_NAME.eq("yiqing")).fetch();
    System.out.println(result);

    Result<Record1<Integer>> result1 = dslContext.selectCount().from(AUTHOR).fetch();
    System.out.println(result1);

    Result<Record1<String>> result2 = dslContext.selectDistinct(AUTHOR.FIRST_NAME).from(AUTHOR)
        .fetch();
    System.out.println(result2);

    Result<Record> result3 = dslContext.select().from(AUTHOR).where(AUTHOR.FIRST_NAME.like("%y%"))
        .fetch();
    System.out.println(result3);

    dslContext.select(AUTHOR.ID,AUTHOR.FIRST_NAME)
        .from(AUTHOR)
        .orderBy(AUTHOR.ID.asc())
        .limit(2)
        .offset(2)
        .fetch()
        .forEach(System.out::println);
  }
}
