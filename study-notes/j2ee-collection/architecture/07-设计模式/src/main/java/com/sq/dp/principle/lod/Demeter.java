package com.sq.dp.principle.lod;

/**
 * 迪米特法则
 * 你非常想去某家公司上班, 但是该公司只有内推渠道, 刚好你朋友的朋友就在该公司上班, 因此你可以找到你朋友的朋友让他帮你内推, 从而获得进入该公司的面试机会
 */
public class Demeter {
    public static void main(String[] args) {
        Person person = new Person();
        person.goToInterview();
    }
}

// 想去的公司
class Company {
    // 内推的简历
    private Resume recommendResume;

    // 前来面试的面试者
    public String interview(Person interviewer) {
        if (this.recommendResume != null && this.recommendResume.getName().equals(interviewer.getName())) {
            return "successd";
        }
        return "failed";
    }

    public void setRecommendResume(Resume recommendResume) {
        this.recommendResume = recommendResume;
    }
}

// 朋友的朋友
class OtherFriend {
    private Company company = new Company();

    public Company recommend(Resume resume) {
        company.setRecommendResume(resume);
        return this.company;
    }
}

// 朋友
class Friend {
    private OtherFriend otherFriend = new OtherFriend();

    public Company proxyRecommend(Resume resume) {
        return otherFriend.recommend(resume);
    }

    // ...setOtherFriend
}

// 你的简历
class Resume {
    private String name;

    public Resume(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

// 想要去面试的人
class Person {
    private String name = "小明";
    private Friend friend = new Friend();
    private Resume resume = new Resume(this.name);

    public String goToInterview() {
        // 1. 让朋友代理找到他朋友推荐
        Company company = friend.proxyRecommend(resume);
        // 2. 去公司面试
        String result = company.interview(this);
        System.out.println("小明面试结果: " + result);
        return result;
    }

    public String getName() {
        return name;
    }
}