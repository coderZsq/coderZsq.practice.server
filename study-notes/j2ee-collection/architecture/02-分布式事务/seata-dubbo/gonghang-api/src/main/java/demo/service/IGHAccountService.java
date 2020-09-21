package demo.service;



public interface IGHAccountService {

    /**
     *
     * @param id 收款人Id
     * @param amount  收款金额
     */
    public void transIn(Long id, Integer amount);

}
