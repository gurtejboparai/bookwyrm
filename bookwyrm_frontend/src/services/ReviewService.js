import { bookwyrmClient } from '@/client/AxiosClient';

export default {
    
    postReview(name, author, description, commentList, anonymous){
        return bookwyrmClient.post('/api/review/',{name:name, author:author, description:description, commentList:commentList, anonymous:anonymous});
    }
}
