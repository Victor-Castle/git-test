package com.victor.controller;

import com.victor.anno.Log;
import com.victor.pojo.Dept;
import com.victor.pojo.Result;
import com.victor.service.DeptService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/depts")
@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;

    //@RequestMapping(value = "/depts",method = RequestMethod.GET)
    @GetMapping
    public Result list(){
//        System.out.println("查询全部部门数据");
        log.info("查询全部部门数据");
        List<Dept> deptList = deptService.findAll();
        return Result.success(deptList);
    }


    //删除部门-方式一
/*    @DeleteMapping("/depts")
    public Result delete(HttpServletRequest request){
        String idStr = request.getParameter("id");
        int id = Integer.parseInt(idStr);
        System.out.println("根据ID删除部门:"+id);
        return Result.success();
    }
*/

    //删除部门-方式二：@RequestParam
    //注意事项：一旦声明了@RequestParam注解，该参数在请求时必须传递，如果不传递将会报错，（默认 required = true）
//    @DeleteMapping("/depts")
//    public Result delete(@RequestParam(value = "id",required = false) Integer deptId){
//        System.out.println("根据ID删除部门:"+deptId);
//        return Result.success();
//    }

    //删除部门-方式三：省略@RequestParam（前端传递的请求参数名与服务器方法形参名一致）
    @Log
    @DeleteMapping
    public Result delete(Integer id){
//        System.out.println("根据ID删除部门:"+ id);
        log.info("根据ID删除部门: {}", id);
        deptService.deleteById(id);
        return Result.success();
    }


    @Log
    @PostMapping
    public Result add(@RequestBody Dept dept){
//        System.out.println("添加部门："+dept);
        log.info("添加部门：{}",dept);
        deptService.add(dept);
        return Result.success();
    }

    //修改部门
//    @GetMapping("/depts/{id}")
//    public Result getInfo(@PathVariable("id") Integer deptId){
//        System.out.println("查询部门ID:"+deptId);
//        Result result = Result.success();
//        return result;
//    }


    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id){
//        System.out.println("查询部门ID:"+id);
        log.info("查询部门ID:{}",id);
        Dept dept = deptService.getById(id);
        Result result = Result.success(dept);
        return result;
    }

    /**
     * 修改部门 - PUT http://localhost:8080/depts  请求参数：{"id":1,"name":"研发部"}
     */
    @Log
    @PutMapping
    public Result update(@RequestBody Dept dept){
//        System.out.println("修改部门, dept=" + dept);
        log.info("修改部门, dept={}",dept);
        deptService.update(dept);
        return Result.success();
    }
}
