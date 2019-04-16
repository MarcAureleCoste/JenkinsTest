def mySupaFunc(String ecrURL, String imageName, String tag) {
    def img="$ecrURL/$imageName:$tag"
    def test="$ecrURL/$imageName:$GIT_COMMIT"
    sh """
        echo 'SUPA FUNCTION !'
        ls -l
        echo $img
        echo $test
    """
}
return this
