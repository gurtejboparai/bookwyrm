import {shallowMount} from '@vue/test-utils'
import ReviewComponent from '@/components/ReviewComponent.vue'

describe('ReviewComponent.vue Test', ()=> {

    it('renders the component', ()=> {
        const wrapper = shallowMount(ReviewComponent, {
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