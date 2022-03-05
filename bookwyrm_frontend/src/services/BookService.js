import { bookwyrmClient } from '@/client/AxiosClient';

export default {
  uploadBook(title, author){
    return bookwyrmClient.post('/api/book/',{title:title, author:author});
  },
  searchBook(title){
    return bookwyrmClient.get('/api/book/'+title);
  }
}