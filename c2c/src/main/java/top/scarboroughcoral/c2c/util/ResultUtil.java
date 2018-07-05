package top.scarboroughcoral.c2c.util;

import org.apache.poi.ss.formula.functions.T;
import top.scarboroughcoral.c2c.model.result.BaseResult;

public class ResultUtil {



    public static <T extends Object> BaseResult getBaseResult(){
        return new BaseResult<T>();
    }
}
