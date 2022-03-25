import { bookwyrmClient } from '@/client/AxiosClient';

export default {
  signup(username, passwordhash){
    return bookwyrmClient.post('/api/user/',{username:username, passwordHash:passwordhash});
  },
  signin(username, passwordhash){
    return bookwyrmClient.put('/api/user/login',{username:username, passwordHash:passwordhash});
  }
}