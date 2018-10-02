package kr.co.petApplication.utils;

public class Global {
	
	/**********     카테고리 관련     **********/
    //구분 Index
    public final static int CATEGORY_GENERAL = 0; //애견동반
    public final static int CATEGORY_SPECIAL = 1; //애견전용

    // 애견동반
    public final static String[] CATEGORY_GENERAL_STR_ARR = {"일반카페","일반식당","일반주점","일반숙박","공원","일반스튜디오"};
    public final static int CATEGORY_GENERAL_LENGTH = CATEGORY_GENERAL_STR_ARR.length;
    public final static Integer CATEGORY_GENERAL_CAFE = 1;
    public final static Integer CATEGORY_GENERAL_RESTAURANT = 2;
    public final static Integer CATEGORY_GENERAL_BAR = 3;
    public final static Integer CATEGORY_GENERAL_ACCOMMODATION = 4;
    public final static Integer CATEGORY_GENERAL_PARK = 5;
    public final static Integer CATEGORY_GENERAL_STUDIO = 6;
    public final static Integer[] CATEGORY_GENENRAL_ID_ARR = {CATEGORY_GENERAL_CAFE,CATEGORY_GENERAL_RESTAURANT,CATEGORY_GENERAL_BAR,CATEGORY_GENERAL_ACCOMMODATION,CATEGORY_GENERAL_PARK,
                                                 CATEGORY_GENERAL_STUDIO};

    // 애견동반 , 애견전용 데이터 구별하는 수 ( "애견전용" 필드의 최소값 )
    public final static Integer CATEGORY_DIVISION_NUM= 100;

    // 애견전용
    public final static String[] CATEGORY_SPECIAL_STR_ARR = {"애견카페","펫샵","애견미용","동물병원","애견분양","애견교육","애견숙박","애견레저","애견장묘","애견놀이터","애견스튜디오"};
    public final static int CATEGORY_SPECIAL_LENGTH = CATEGORY_SPECIAL_STR_ARR.length;
    public final static Integer CATEGORY_SPECIAL_CAFE = 101;
    public final static Integer CATEGORY_SPECIAL_PETSHOP = 102;
    public final static Integer CATEGORY_SPECIAL_BEAUTY = 103;
    public final static Integer CATEGORY_SPECIAL_HOSPITAL = 104;
    public final static Integer CATEGORY_SPECIAL_ADOPT = 105;
    public final static Integer CATEGORY_SPECIAL_EDUCATION = 106;
    public final static Integer CATEGORY_SPECIAL_ACCOMMODATION = 107;
    public final static Integer CATEGORY_SPECIAL_LEISURE = 108;
    public final static Integer CATEGORY_SPECIAL_FUNERAL = 109;
    public final static Integer CATEGORY_SPECIAL_PLAYGROUND = 110;
    public final static Integer CATEGORY_SPECIAL_STUDIO = 111;
    public final static Integer[] CATEGORY_SPECIAL_ID_ARR = {CATEGORY_SPECIAL_CAFE,CATEGORY_SPECIAL_PETSHOP,CATEGORY_SPECIAL_BEAUTY,CATEGORY_SPECIAL_HOSPITAL,CATEGORY_SPECIAL_ADOPT,
                                                  CATEGORY_SPECIAL_EDUCATION,CATEGORY_SPECIAL_ACCOMMODATION,CATEGORY_SPECIAL_LEISURE,CATEGORY_SPECIAL_FUNERAL,CATEGORY_SPECIAL_PLAYGROUND,
                                                CATEGORY_SPECIAL_STUDIO};
    
    /**********     지역 관련    		**********/
	 public final static String[] LOCATION_GU_STR_ARR = {
			 "서울전체","강남구","강북구","강서구","관악구","광진구","구로구","금천구","노원구","도봉구","동대문구",
			 "동작구","마포구","서대문구","서초구","성동구","성북구","송파구","양천구","영등포구","용산구","은평구",
			 "종로구","중구","중랑구"
	 		};
	 
	 
	   
}
