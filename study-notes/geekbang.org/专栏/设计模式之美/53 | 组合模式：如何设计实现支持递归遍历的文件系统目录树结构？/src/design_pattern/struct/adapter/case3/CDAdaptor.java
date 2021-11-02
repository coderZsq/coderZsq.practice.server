package design_pattern.struct.adapter.case3;

// 注意：适配器类的命名不一定非得末尾带Adaptor
public class CDAdaptor extends CD implements ITarget {
    //...
    @Override
    public void function1() {
        super.staticFunction1();
    }

    @Override
    public void function2() {
        super.uglyNamingFunction2();
    }

    @Override
    public void function3(ParamsWrapperDefinition paramsWrapper) {
        super.tooManyParamsFunction3(paramsWrapper.getParamA(), paramsWrapper.getParamB());
    }

    @Override
    public void function4() {
        //...reimplement it...
    }
}