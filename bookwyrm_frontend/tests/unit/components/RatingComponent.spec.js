import {shallowMount} from '@vue/test-utils'
import RatingComponent from '@/components/RatingComponent.vue'
import {createStore} from 'vuex'
import {createApp} from 'vue'


const store = createStore({
    state() {
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
const app = createApp(RatingComponent)
app.use(store)

describe('RatingComponent.vue Test', ()=> {

    it('renders the component', ()=> {
        const wrapper = shallowMount(RatingComponent, {
            global: {
                plugins: [store]
            },
            propsData:{
                'localRatings': [],
                'nextNewRatingId': 0
            }
        })
        expect(wrapper.vm.$options.name).toMatch('RatingComponent')
    })
    it('processes valid prop data', ()=> {
        const wrapper = shallowMount(RatingComponent, {
            global: {
                plugins: [store]
            },
            propsData:{
                'localRatings': [],
                'nextNewRatingId': 0
            }
        })

        wrapper.setData({localRatings: [{ratingId:0, genre:'Gothic', score:2.5}]})
        wrapper.setData({nextNewRatingId: 1})
        expect(wrapper.vm.nextNewRatingId).toEqual(1)
        expect(wrapper.vm.localRatings[0]).toEqual({ratingId:0, genre:'Gothic', score:2.5})
    })
})