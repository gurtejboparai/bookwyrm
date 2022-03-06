import { bookwyrmClient } from '@/client/AxiosClient';

export default {
    postReview(bookId, author, content, anonymousFlag){
        return bookwyrmClient.post('/api/review/',{bookId:bookId, author:author, content:content, anonymousFlag:anonymousFlag});
    }
}
