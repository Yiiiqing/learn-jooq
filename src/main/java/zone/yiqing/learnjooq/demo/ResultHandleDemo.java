package zone.yiqing.learnjooq.demo;

import org.jooq.DSLContext;
import zone.yiqing.learnjooq.config.DslContextConfig;
import zone.yiqing.learnjooq.generated.tables.pojos.Author;

import static zone.yiqing.learnjooq.generated.Tables.*;

import java.util.List;

/**
 * @author yiqing.zhang, {@literal <yiqing.zhang@leyantech.com>}
 * @date 2021-06-29.
 */
public class ResultHandleDemo {

  public static void main(String[] args) {
    resultHandle();
  }
  public static void resultHandle(){
    DSLContext dslContext = DslContextConfig.getDslContext();
    List<Author> authors = dslContext.select()
        .from(AUTHOR)
        .where(AUTHOR.LAST_NAME.eq("zhang"))
        .fetch(r -> r.into(Author.class));
    System.out.println(authors);

    List<Author> into = dslContext.select()
        .from(AUTHOR)
        .where(AUTHOR.LAST_NAME.eq("zhang"))
        .fetchInto(Author.class);
    System.out.println(into);
  }
}
