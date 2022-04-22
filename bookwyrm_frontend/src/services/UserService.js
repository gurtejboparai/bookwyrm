import { bookwyrmClient } from '@/client/AxiosClient';

export default {
  signup(username, passwordhash){
    return bookwyrmClient.post('/api/user/',{username:username, passwordHash:passwordhash});
  },
  signin(username, passwordhash){
    console.log(passwordhash);
    return bookwyrmClient.put('/api/user/login',{username:username, passwordHash:passwordhash});
  },
  updateUser(username, authorFlag, authorName, profJournalistFlag, profJournalistName){
    return bookwyrmClient.put('/api/user/',{
      username:username, 
      authorFlag:authorFlag, 
      authorName:authorName,
      journalistFlag:profJournalistFlag,
      journalistName:profJournalistName 
    });
  }
}