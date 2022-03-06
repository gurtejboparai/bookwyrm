import { bookwyrmClient } from '@/client/AxiosClient';

export default {
  uploadBook(title, author){
    return bookwyrmClient.post('/api/book/',{title:title, author:author});
  },
  searchBookDetail(id){
    return bookwyrmClient.get('/api/book/details/'+id);
  },
  searchBook(title){
    return bookwyrmClient.get('/api/book/'+title);
  }
}