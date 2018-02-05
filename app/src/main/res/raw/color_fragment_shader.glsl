precision mediump float;

varying vec4 color;
varying vec2 pass_textureCoords;

uniform sampler2D textureSampler;

void main()
{
    gl_FragColor = texture2D(textureSampler, pass_textureCoords) * color;
}