package javaSE;


public class TestMultiImpl implements TestInterface02,TestInterface01{
    @Override
    public boolean isEqual(int i1, int o2) {
        return i1==o2?true:false;
    }
}
