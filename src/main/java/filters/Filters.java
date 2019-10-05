package filters;

import static spark.Spark.before;

public class Filters {

    public void filtrar(){
        before((request, response) -> {
            response.header("Foo", "Set by second before filter");
        });

    }
}
