import { bookwyrmClient } from '@/client/AxiosClient';

export default {
    postReview(bookId, author, content, anonymousFlag, ratingsList, journalistFlag, journalistName){
        return bookwyrmClient.post('/api/review/',
        {
            bookId:bookId, 
            author:author, 
            content:content, 
            anonymousFlag:anonymousFlag, 
            ratings:ratingsList, 
            journalistFlag:journalistFlag,
            journalistName:journalistName
        });
    },

    updateVoting(userId, reviewId, voteValue) {
        return bookwyrmClient.put('/api/review/voting',
        {
            userId:userId,
            reviewId:reviewId,
            voteValue:voteValue
        });
    }
}
