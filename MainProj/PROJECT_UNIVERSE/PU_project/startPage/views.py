from django.shortcuts import render

# Create your views here.

def startP(request):
    return render(request,"startPages.html")