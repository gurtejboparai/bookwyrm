import { bookwyrmClient } from '@/client/AxiosClient';

export default {
  uploadBook(title, author, desc, isbn){
    return bookwyrmClient.post('/api/book/',{title:title, author:author, desc:desc, isbn:isbn});
  },
  searchBookDetail(id){
    return bookwyrmClient.get('/api/book/details/'+id);
  },
  searchBook(title){
    return bookwyrmClient.get('/api/book/'+title);
  },
  searchBookViaGoogle(isbn){
    return bookwyrmClient.get(`/api/book/deepsearch/`+isbn);
  },
  searchBookUpdate(id, newDesc, newGenre){
    return bookwyrmClient.put(`/api/book/`,{
      desc: newDesc,
      id:id,
      genre:newGenre
    });
  }
}