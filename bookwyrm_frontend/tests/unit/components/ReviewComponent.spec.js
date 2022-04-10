import {shallowMount} from '@vue/test-utils'
import ReviewComponent from '@/components/ReviewComponent.vue'
import {createStore} from 'vuex'
import {createApp} from 'vue'


const store = createStore({
    state () {
        return {
        username: "",
        genreList: [
                "Overall",
                "Adventure",
                "Action",
                "Bedtime",
                "Comedy/Humor",
                "Children's",
                "Drama",
                "Fantasy",
                "Gothic",
                "Horror",
                "Historical Fiction",
                "Alternate History",
                "Mystery",
                "Romance",
                "Sport",
                "Science-Fiction",
                "Thriller"
            ]
        }
    },
    getters: {
        getGenres(state){
            return state.genreList
        },
        getStrictGenres(state){
            return state.genreList.slice(1, state.genreList.length)
        }
    }
})
const app = createApp(ReviewComponent)
app.use(store)

describe('ReviewComponent.vue Test', ()=> {

    it('renders the component', ()=> {
        const wrapper = shallowMount(ReviewComponent, {
            global: {
                plugins: [store]
            },
            data() {
                return {
                    focused: false,
                    topic: null,
                    newCommentText: "",
                    newReviewText:""
                }
            }
        })
        expect(wrapper.vm.$options.name).toMatch('ReviewComponent')
    })
    it('processes valid prop data', ()=> {
        const wrapper = shallowMount(ReviewComponent, {
            global: {
                plugins: [store]
            },
            data() {
                return {
                    focused: false,
                    topic: null,
                    newCommentText: "",
                    newReviewText:""
                }
            }
        })
        wrapper.setData({newCommentText: 'Comment on it!'})
        wrapper.setData({newReviewText: 'Review on it!'})
        expect(wrapper.vm.newCommentText).toMatch('Comment on it!')
        expect(wrapper.vm.newReviewText).toMatch('Review on it!')
    })
})