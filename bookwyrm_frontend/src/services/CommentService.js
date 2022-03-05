import { bookwyrmClient } from '@/client/AxiosClient';

export default {
  uploadComment(reviewId, author, content, anonymousFlag){
    return bookwyrmClient.post('/api/comment/',{reviewId:reviewId, author:author, content:content, anonymousFlag:anonymousFlag});
  }
}