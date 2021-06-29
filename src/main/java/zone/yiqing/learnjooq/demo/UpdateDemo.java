package zone.yiqing.learnjooq.demo;

import org.jooq.DSLContext;
import zone.yiqing.learnjooq.config.DslContextConfig;
import static zone.yiqing.learnjooq.generated.Tables.*;

import static org.jooq.impl.DSL.select;

/**
 * @author yiqing.zhang, {@literal <yiqing.zhang@leyantech.com>}
 * @date 2021-06-29.
 */
public class UpdateDemo {

  public static void main(String[] args) {
    update();
  }

  public static void update(){
    DSLContext dslContext = DslContextConfig.getDslContext();
    dslContext.update(AUTHOR).set(AUTHOR.FIRST_NAME,"san2")
        .where(AUTHOR.FIRST_NAME.eq("san"))
        .execute();

    dslContext.update(AUTHOR)
        .set(AUTHOR.FIRST_NAME,
            select(AUTHOR.FIRST_NAME).from(AUTHOR).where(AUTHOR.LAST_NAME.eq("li")))
        .where(AUTHOR.LAST_NAME.eq("wang"))
        .execute();
  }
}
