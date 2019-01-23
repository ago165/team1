package dev.mvc.snackzone;

import java.util.HashMap;
import java.util.List;



public interface SnackzoneProcInter {
  /**
   * <Xmp>
   * ������ �׷� ���
   * <insert id="create" parameterType="SnackzoneVO">
   * </Xmp>
   * @param snackzoneVO
   * @return 
   */
  public int create(SnackzoneVO snackzoneVO);
  
  /**
   * �˻���� + ����¡
   * <Xmp>
   * <select id="list" resultType="SnackzoneVO">
   * </Xmp>
   * 
   * @return
   */
  public List<SnackzoneVO> list(HashMap<String, Object> hashMap);
  
  /**
   * category�� �˻��� ���ڵ� ����
   * @return
   */
  public int search_count(HashMap hashMap);
  
  /** 
   * SPAN�±׸� �̿��� �ڽ� ���� ����, 1 ���������� ���� 
   * ���� ������: 11 / 22   [����] 11 12 13 14 15 16 17 18 19 20 [����] 
   *
   * @param categoryno ī�װ���ȣ 
   * @param search_count �˻�(��ü) ���ڵ�� 
   * @param nowPage     ���� ������
   * @param word �˻���
   * @return ����¡ ���� ���ڿ�
   */ 
  public String paging(int search_count, int nowPage, String word);
  
  /**
   * ��ȸ
   * <xmp>
   *   <select id="read" resultType="SnackzoneVO" parameterType="int">
   * </xmp>  
   * @param categrpno
   * @return
   */
  public SnackzoneVO read (int snackno);
  
  /**
   * ����ó��
   * @param vo
   * @return
   */
  public int update(SnackzoneVO snackzoneVO);
  
  
  /**
   * ����
   * @param snackno
   * @return
   */
  public int delete(int snackno);
  

 /* *//**
   * �˻� ���
   * @param snackno
   * @return
   *//*
  public List<SnackzoneVO> list_search(HashMap hashMap);

  *//**
   *  �˻��� ���ڵ� ����
   * @return
   *//*
  public int search_count(HashMap hashMap);*/
}
