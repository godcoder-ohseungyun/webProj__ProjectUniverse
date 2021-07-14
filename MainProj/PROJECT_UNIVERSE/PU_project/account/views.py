from django.contrib.auth import authenticate, login , logout
from django.shortcuts import render, redirect
from account.forms import RegisterForm

#django.contrib.auth 기본 앱을 제공
#from django.contrib.auth.models import User #유저 모델 api


#별도에 app에 관리

# Create your views here.

def loginP(request):
    #return render(request,"loginPage.html")
    if request.method == "POST": #로그인 시(데이터 송시) data 저장
        username= request.POST.get('username')
        password = request.POST.get('password')
        user = authenticate(request=request,username=username,password=password)
        if user is not None: #유저가 저장되었으면
            login(request,user) #로그인 후
            return redirect("startPage")# 홈으로 이동

    return render(request,'loginPage.html')


def logoutP(request):
    logout(request)
    return redirect("startPage")



def signUpP(request):
    if request.method == "POST":
        form = RegisterForm(request.POST)
        if form.is_valid(): #유효성 검사 method
            form.save()
            username = form.cleaned_data.get('username')
            password = form.cleaned_data.get('password1')
            nickname = form.cleaned_data.get('nickname')
            #유저 객체 생성
            user = authenticate(request=request,username=username,password=password,nickname=nickname)
            login(request, user)
            return redirect('startPage')
    else:
        form = RegisterForm()
    return render(request, 'signUpPage.html')