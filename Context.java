/* 413226087 李柏翰 */
/* 413085043 謝慶章 */

public class Context {
    IStrategy strategy;

    public void service() {
        if (strategy == null) {
            strategy = new RegisterAccount();
        }

        strategy.execute();
    }

    public void choiceStrategy(IStrategy strategy) {
        this.strategy = strategy;
    }

    public String getName() {
        return "UBike Share Company";
    }
}
