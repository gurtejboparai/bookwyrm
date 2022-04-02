import {shallowMount} from '@vue/test-utils'
import StarRatingWrapperComponent from '@/components/StarRatingWrapperComponent.vue'

describe('StarRatingWrapperComponent.vue Test', ()=> {

    it('renders the component', ()=> {
        const wrapper = shallowMount(StarRatingWrapperComponent, {
            propsData:{
                'score': 0.5,
                'ratingId': 0,
                'displayOnly': false
            }
        })
        expect(wrapper.vm.$options.name).toMatch('StarRatingWrapperComponent')
    })
    it('processes valid prop data', ()=> {
        const wrapper = shallowMount(StarRatingWrapperComponent, {
            propsData:{
                'score': 2.5,
                'ratingId': 1,
                'displayOnly': true
            }
        })
        //props can't be changed and this componenet only has props
        expect(wrapper.vm.score).toEqual(2.5)
        expect(wrapper.vm.ratingId).toEqual(1)
        expect(wrapper.vm.displayOnly).toBe(true)
    })
})