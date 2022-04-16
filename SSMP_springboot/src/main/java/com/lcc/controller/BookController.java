package com.lcc.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lcc.controller.utils.R;
import com.lcc.domain.Book;
import com.lcc.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping
    public R getAll(){
        return new R(true,bookService.getAll());
    }

    @PostMapping
    public R save(@RequestBody Book book){
        return new R(bookService.save(book));
    }

    @PutMapping
    public R update(@RequestBody Book book){
        return new R(bookService.update(book));
    }

    @DeleteMapping("{id}")
    public R delete(@PathVariable Integer id){
        return new R(true,bookService.delete(id));
    }

    @GetMapping("{id}")
    public R getById(@PathVariable Integer id){
        return new R(true,bookService.getById(id));
    }
/*
    @GetMapping("{currentPage}/{pageSize}")
    public R getPage(@PathVariable int currentPage, @PathVariable int pageSize){
        IPage<Book> page = bookService.getPage(currentPage, pageSize);
        //如果当前页码值大于了总页码值，那么重新执行查询操作，使用最大页码值作为当前页码值
        if( currentPage > page.getPages()){
            page = bookService.getPage((int)page.getPages(), pageSize);
        }
        return new R(true, page);
    }
*/
    @GetMapping("{currentPage}/{pageSize}")
    public R getAll(@PathVariable int currentPage,@PathVariable int pageSize,Book book) {
        System.out.println("参数=====>"+book);
        IPage<Book> pageBook = bookService.getPage(currentPage,pageSize,book);
        return new R(null != pageBook ,pageBook);
    }
}
