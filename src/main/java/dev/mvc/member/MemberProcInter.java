package dev.mvc.member;

import java.util.List;
import java.util.HashMap;
import javax.servlet.http.HttpSession;






public interface MemberProcInter {

    /**
     * �ߺ� ���̵� �˻�
     * @param id
     * @return �ߺ� ���̵� ����
     */
    public int checkId(String id);
    
    /**
    ȸ�� ���
    @param memberVO
    @return ��ϵ� ȸ���� 1 or 0
    */
    public int create(MemberVO memberVO);
    
    /**
     * ȸ�� ��ü ���
     * @return
     */
/*    public List<MemberVO> list();*/
    
    // �˻��� ���ڵ� ����
    public int search_count(HashMap hashMap);
    
    // �˻� ��� + ����¡
    public List<MemberVO> list(HashMap<String, Object> hashMap);
    
    public String paging(int search_count, int nowPage, String s_word);

    
    /**
     * �α��ε� ȸ�� �������� �˻��մϴ�.
     * @param request
     * @return true: ������
     */
    public boolean isMember(HttpSession session);
    
    /**
     * 1���� ���ڵ� ��ȸ
     * @param memberno
     * @return
     */
    public MemberVO read(int memberno);

    /**
     * ���̵� ��ȸ
     * @param id
     * @return
     */
    public MemberVO readById(String id);

/**
 * ����
 * @param memberVO
 * @return
 */
    public int update(MemberVO memberVO);
    
    
    
    
    
    
    
    
    /**
     * ���ڵ� 1�� ����
     * <delete id="delete" parameterType="int">
     * @param memberno ������ ȸ�� ��ȣ
     * @return ������ ���ڵ� ����
     */
    public int delete(int memberno);
    
    
    
    
    
    
    
    
    
 
}









