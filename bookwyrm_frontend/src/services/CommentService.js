import { bookwyrmClient } from '@/client/AxiosClient';

export default {
  uploadComment(content, author, anonymousFlag, reviewId){
    return bookwyrmClient.post('/api/comment/',{content:content, author:author, anonymousFlag:anonymousFlag, reviewId:reviewId});
  }
}