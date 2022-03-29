import { bookwyrmClient } from '@/client/AxiosClient';

export default {
    postReview(bookId, author, content, anonymousFlag, ratingsList, journalistFlag, journalistName){
        return bookwyrmClient.post('/api/review/',
        {
            bookId:bookId, 
            author:author, 
            content:content, 
            anonymousFlag:anonymousFlag, 
            ratingsList, 
            journalistFlag:journalistFlag,
            journalistName:journalistName
        });
    }
}
