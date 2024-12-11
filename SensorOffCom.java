/* 413226087 李柏翰 */
/* 413085043 謝慶章 */

import java.util.*;

public class SensorOffCom implements Command{
    Sensor sensor;
    public SensorOffCom(Sensor sensor){
        this.sensor=sensor;
    }
    public void execute(){
        sensor.switchOff();
    }
}
