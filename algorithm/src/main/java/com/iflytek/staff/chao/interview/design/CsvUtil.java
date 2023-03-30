//package com.iflytek.staff.chao.interview.design;
//
//import com.opencsv.CSVReader;
//import com.opencsv.bean.CsvToBean;
//import com.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;
//
//import java.io.FileInputStream;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//public class CsvUtil {
//
//
//    /**
//     * 　　* @author Lyy
//     * 　　* @param entityClass excel中每一行数据的实体类
//     * 　　* @param in          excel文件
//     * 　　* @param fields     字段名字
//     * 　　*             需要注意的是这个方法中的map中：
//     * 　　*             excel表格中每一列名为键，每一列对应的实体类的英文名为值
//     * 　　 * @throws Exception
//     */
//    public static <T> List<T> ExcelToList(InputStream in, Class<T> entityClass,
//                                          Map<String, String> fields) {
//
//        CSVReader reader = new CSVReader(new InputStreamReader(in));
//
//        HeaderColumnNameTranslateMappingStrategy mapper = new HeaderColumnNameTranslateMappingStrategy();
//        mapper.setType(entityClass.getClass());
//        mapper.setColumnMapping(fields);
//
//        CsvToBean csvToBean = new CsvToBean();
//        csvToBean.setCsvReader(reader);
//        csvToBean.setMappingStrategy(mapper);
//
//        return csvToBean.parse();
//    }
//
//    public void testCsvRead2() {
//
//        String filePath = "./students.csv";
//        Map<String, String> fields = new HashMap<String, String>();
//        fields.put("name", "姓名");
//        fields.put("majorName", "专业");
//
//        try {
//
//            InputStream in = new FileInputStream(filePath);
//            List<Student> resultList = CsvUtil.ExcelToList(in, Student.class, fields);
//            System.out.println("loadFacilityData size :" + resultList.size());
//        } catch (Exception e) {
//
//        }
//    }
//}
