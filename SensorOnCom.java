/* 413226087 李柏翰 */
/* 413085043 謝慶章 */

import java.util.*;

public class SensorOnCom implements Command{
    Sensor sensor;
    public SensorOnCom(Sensor sensor){
        this.sensor=sensor;
    }
    public void execute(){
        sensor.switchOn();
    }
}
