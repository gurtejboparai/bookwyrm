import {shallowMount} from '@vue/test-utils'
import RatingComponent from '@/components/RatingComponent.vue'

describe('RatingComponent.vue Test', ()=> {

    it('renders the component', ()=> {
        const wrapper = shallowMount(RatingComponent, {
            propsData:{
                'localRatings': [],
                'nextNewRatingId': 0
            }
        })
        expect(wrapper.vm.$options.name).toMatch('RatingComponent')
    })
    it('processes valid prop data', ()=> {
        const wrapper = shallowMount(RatingComponent, {
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