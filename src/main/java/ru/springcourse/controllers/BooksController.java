package ru.springcourse.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.springcourse.dao.BookDAO;
import ru.springcourse.dao.PersonDAO;
import ru.springcourse.models.Book;
import ru.springcourse.models.Person;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BooksController {

    private final BookDAO bookDAO;
    private final PersonDAO personDAO;

    @Autowired
    public BooksController(BookDAO bookDao, PersonDAO personDAO) {
        this.bookDAO = bookDao;
        this.personDAO = personDAO;
    }

    //запрос на получение страницы со списком всех книг
    @GetMapping()
    public String allBooksPage(@NotNull Model model) {
        model.addAttribute("books", bookDAO.index());
        return "books/all-books";
    }
    //запрос на получение страницы с определенной книгой
    @GetMapping("/{id}")
    public String bookPage(@PathVariable int id, Model model, @ModelAttribute("person") Person person) {

    }
    //запрос на получение страницы добавления книги
    @GetMapping("/new")
    public String newBookPage(@ModelAttribute("book") Book book) {
//        model.addAttribute("book", new Book());
        return "books/new-book";
    }
    //запрос на добавление новой книги
    @PostMapping()
    public String createBook(@ModelAttribute("book") @Valid Book book,
                             @NotNull BindingResult bindingResult) {

        return "redirect:/books";
    }
    //запрос на получение страницы изменения книги
    @GetMapping("/{id}/edit")
    public String editBookPage(@PathVariable int id, Model model) {
        return "redirect:/books";
    }

    //запрос на редактирование данных книги
    @PatchMapping("/{id}")
    public String edit(@PathVariable int id, @ModelAttribute("book") @Valid Book updatedBook,
                       BindingResult bindingResult) {
        return "redirect:/books/" + id;
    }

    //запрос на удаление книги
    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        return "redirect:/books";
    }

    @PatchMapping("/{id}/assign")
    public String assign(@PathVariable int id, @ModelAttribute("person") Person selectedPerson) {
        return "redirect:/books/" + id;
    }

    @PatchMapping("/{id}/release")
    public String release(@PathVariable int id) {
        return "redirect:/books/" + id;
    }
}
