package caffeine.net.msg;

public class Query extends Msg {
  private final String[] param;

  public Query(String request) {
    super(request);
    param = request.split(" ");
  }

  @Override
  public String help() {
    return "get <Class> [<ID>]";
  }

  public String[] param() {
    return param;
  }

  public String type() {
    return param[1];
  }

  @Override
  public String toString() {
    String result = "";
    for (String element : param) {
      result += element + " ";
    }
    return result;
  }
}
