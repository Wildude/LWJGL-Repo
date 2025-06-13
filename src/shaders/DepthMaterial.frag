out vec4 fragColor;
struct Shadow
{
    // direction of light that casts shadow
    vec3 lightDirection;
    // data from camera that produces depth texture
    mat4 projectionMatrix;
    mat4 viewMatrix;
    // texture that stores depth values from shadow camera
    sampler2D depthTexture;
    // regions in shadow multiplied by (1-strength)
    float strength;
    // reduces unwanted visual artifacts
    float bias;
};
uniform bool useShadow;
uniform Shadow shadow0;
in vec3 shadowPosition0;
out vec3 color;
void main()
{
    float z = gl_FragCoord.z;
    if (useShadow)
    {
        // determine if surface is facing towards light direction
        float cosAngle = dot( normalize(normal), -normalize(shadow0.lightDirection) );
        bool facingLight = (cosAngle > 0.01);
        // convert range [-1, 1] to range [0, 1]
        //  for UV coordinate and depth information
        vec3 shadowCoord = ( shadowPosition0.xyz + 1.0 ) / 2.0;
        float closestDistanceToLight = texture2D(
        shadow0.depthTexture, shadowCoord.xy).r;
        float fragmentDistanceToLight = clamp(shadowCoord.z, 0, 1);
        // determine if fragment lies in shadow of another object
        bool inShadow = ( fragmentDistanceToLight > 
        closestDistanceToLight + shadow0.bias );
        if (facingLight && inShadow)
        {
            float s = 1.0 - shadow0.strength;
            color *= vec4(s, s, s, 1);
        }
    }
    fragColor = vec4(z, z, z, 1.0);
}