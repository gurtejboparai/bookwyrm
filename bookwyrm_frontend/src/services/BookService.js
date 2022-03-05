import { bookwyrmClient } from '@/client/AxiosClient';

export default {
  uploadBook(name, author){
    return bookwyrmClient.post('/api/book/',{name:name, author:author});
  },
  searchBookDetail(title){
    return bookwyrmClient.get('/api/book/details/'+title);
  }
}