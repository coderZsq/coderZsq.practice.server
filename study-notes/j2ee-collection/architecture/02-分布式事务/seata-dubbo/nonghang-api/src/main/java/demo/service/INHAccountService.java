package demo.service;


public interface INHAccountService {
    //转出操作
    public void transOut(Long id, Integer amount) throws Exception;

    //业务处理
    public void trans(Long outId, Long inId, Integer amount) throws Exception;
}
