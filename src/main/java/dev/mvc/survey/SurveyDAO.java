package dev.mvc.survey;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("dev.mvc.survey.SurveyDAO") // DBMS 저장소 접근 
public class SurveyDAO implements SurveyDAOInter{
  @Autowired  // 빈을 스프링이 만들어서 자동 할당, 개발자는 new 사용 안함.
  private SqlSessionTemplate sqlSessionTemplate = null;
  
  public SurveyDAO() {
    System.out.println("--> SurveyDAO created.");
  }
  
  @Override
  public int create(SurveyVO surveyVO){
    int count = sqlSessionTemplate.insert("survey.create",surveyVO);
    return count;
  }

  @Override
  public List<SurveyVO> list() {
    List<SurveyVO> list = sqlSessionTemplate.selectList("survey.list");
    return list;
  }

  @Override
  public SurveyVO read(int surveyno) {
    SurveyVO surveyVO = sqlSessionTemplate.selectOne("survey.read", surveyno);
    return surveyVO;
  }

  @Override
  public int update(SurveyVO surveyVO) {
    return sqlSessionTemplate.update("survey.update",surveyVO);
  }

  @Override
  public int delete(int surveyno) {
    return sqlSessionTemplate.delete("survey.delete", surveyno);
  }

  @Override
  public int search_count(HashMap hashMap) {
    return sqlSessionTemplate.selectOne("survey.search_count", hashMap);
  }
  
  @Override
  public List<SurveyVO> list_by_survey_search_paging(HashMap<String, Object> hashMap) {
    return sqlSessionTemplate.selectList("survey.list_by_survey_search_paging",hashMap);
  }
  
}
